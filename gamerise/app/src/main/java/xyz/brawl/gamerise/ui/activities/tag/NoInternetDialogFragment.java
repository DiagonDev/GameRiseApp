package xyz.brawl.gamerise.ui.activities.tag;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;

public class NoInternetDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Connessione Assente")
                .setMessage("Nessuna connessione a Internet. Continua offline.")
                .setPositiveButton("OK", (dialog, which) -> dismiss());
        return builder.create();
    }
}
