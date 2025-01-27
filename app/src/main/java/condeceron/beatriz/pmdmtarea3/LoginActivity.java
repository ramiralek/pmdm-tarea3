package condeceron.beatriz.pmdmtarea3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import condeceron.beatriz.pmdmtarea3.databinding.ActivityLoginBinding;
import condeceron.beatriz.pmdmtarea3.databinding.ActivityRegisterBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.loginUser.getText().toString();
                String pass = binding.loginPass.getText().toString();

                if (!user.isEmpty() && !pass.isEmpty())
                    mAuth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);

                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, R.string.authentication_failed,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
            }
        });

        binding.noUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}