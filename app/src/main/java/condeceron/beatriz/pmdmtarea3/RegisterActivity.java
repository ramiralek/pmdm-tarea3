package condeceron.beatriz.pmdmtarea3;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import condeceron.beatriz.pmdmtarea3.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = binding.registerUser.getText().toString();
                String pass = binding.registerPass.getText().toString();

                if (!userEmail.isEmpty() && !pass.isEmpty())
                    mAuth.createUserWithEmailAndPassword(userEmail, pass)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        Map<String, Object> userData = new HashMap<>();
                                        userData.put("email", userEmail);

                                        db.collection("users")
                                                .document(user.getUid())
                                                .set(userData);

                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, R.string.authentication_failed,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
            }
        });

        binding.alreadyUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}