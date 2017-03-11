package edu.tacoma.uw.plsanch.gitgud.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.tacoma.uw.plsanch.gitgud.R;
import edu.tacoma.uw.plsanch.gitgud.util.Account;

/**
 * The RegisterFragment is a class that supports two text entries for user login and registration
 */
public class RegisterFragment extends Fragment {

    Button regButton;
    EditText email;
    EditText password;

    /**
     * Empty Constructor
     */
    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Calls the super method.
     * @param savedInstanceState is the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * onCreateView inflates the fragment and holds the onClickListener for the Sign in or Register button
     * @param inflater is the inflater
     * @param container is the layout holding the fragment
     * @param savedInstanceState is the saved instance state
     * @return the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        // Inflate the layout for this fragment
        regButton = (Button) view.findViewById(R.id.button);
        email = (EditText) view.findViewById(R.id.editTextEmail);
        password = (EditText) view.findViewById(R.id.editText2);
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LoginActivity parent = (LoginActivity) getActivity();


                if(parent instanceof LoginActivity) {
                    try {
                        parent.register(new Account(email.getText().toString(), password.getText().toString()));
                    } catch(IllegalArgumentException e){
                        if(e.getMessage().equals("Email not Valid")) {
                            Toast.makeText(getActivity()
                                    , "Invalid email"
                                    , Toast.LENGTH_LONG)
                                    .show();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException er) {
                                er.printStackTrace();
                            }
                        } else if (e.getMessage().equals("Password not Valid")){
                            Toast.makeText(getActivity()
                                    , "Invalid password"
                                    , Toast.LENGTH_LONG)
                                    .show();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException err) {
                                err.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        return view;

    }

    /**
     * calls the super method
     * @param context is the context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    /**
     * calls the super method
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
