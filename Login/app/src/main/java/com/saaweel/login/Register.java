package com.saaweel.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button confirmLogin = view.findViewById(R.id.confirmRegister);

        EditText userInput = view.findViewById(R.id.userInput);
        if (mParam1 != null && !mParam1.isEmpty())
            userInput.setText(mParam1);

        EditText passInput = view.findViewById(R.id.passInput);
        if (mParam2 != null && !mParam2.isEmpty())
            passInput.setText(mParam2);

        EditText emailInput = view.findViewById(R.id.emailInput);

        confirmLogin.setOnClickListener(e -> {
            String user = userInput.getText().toString();
            String pass = passInput.getText().toString();
            String email = emailInput.getText().toString();

            if (!user.isEmpty() && !pass.isEmpty() && !email.isEmpty()) {
                LinearLayout loginContainer = view.findViewById(R.id.registerContainer);
                loginContainer.removeAllViews();

                ImageView registeredImage = new ImageView(getContext());
                registeredImage.setImageResource(R.drawable.loggedin);
                loginContainer.addView(registeredImage);
            } else {
                Toast.makeText(getContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}