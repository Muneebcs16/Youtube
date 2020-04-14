package com.example.assignmentmad.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignmentmad.R;

public class UpdateFragment extends Fragment {

    private UpdateViewModel updateViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        updateViewModel =
                ViewModelProviders.of(this).get(UpdateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_update, container, false);
        final TextView textView = root.findViewById(R.id.text_update);
        updateViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
