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
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button confirmLogin = view.findViewById(R.id.confirmLogin);

        EditText userInput = view.findViewById(R.id.userInput);

        EditText passInput = view.findViewById(R.id.passInput);

        confirmLogin.setOnClickListener(e -> {
            String user = userInput.getText().toString();
            String pass = passInput.getText().toString();

            if (!user.isEmpty() && !pass.isEmpty()) {
                if (user.equals("admin") && pass.equals("1234")) {
                    LinearLayout loginContainer = view.findViewById(R.id.loginContainer);
                    loginContainer.removeAllViews();

                    ImageView loggedImage = new ImageView(getContext());
                    loggedImage.setImageResource(R.drawable.loggedin);
                    loginContainer.addView(loggedImage);
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Register().newInstance(user, pass)).commit();
                }
            } else {
                Toast.makeText(getContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}