package ${defaultPackage}

import android.app.Activity
import android.app.AlertDialog
import groovy.transform.CompileStatic

/**
 * This is an example on how you can create useful extensions with Groovy and add functionality to your
 * activities easily.
 */
@CompileStatic
class Widgets {

    /**
     * This extension allows any {@link android.app.Activity} to have a helper method to build a
     * dialog and open it:
     *
     * <pre>
     *     showDialogWithLayout(R.layout.about) {
     *       icon  = R.drawable.ic_launcher
     *       title = getString(R.string.app_name)
     *   }
     * </pre>
     *
     * @param shelf it's the activity itself. Notice that you don't have to pass the activity as parameter.
     * @param int layout The layout that will be used in the dialog
     * @param settings all the settings of the {@link android.app.AlertDialog.Builder }
     *
     */
    static void showDialogWithLayout(final Activity shelf, int layout, @DelegatesTo(AlertDialog.Builder) final Closure<?> settings) {
        /* Using the builder with the activity and the chosen layout */
        AlertDialog.Builder builder =
            new AlertDialog.Builder(shelf)
                .setView(shelf.layoutInflater.inflate(layout, null, false))

        /* Passing the settings to the builder. Technically we're
         making the builder to resolve the variables against the
         closure (The closure will be the delegate of the builder) */
        builder.with(settings)

        /* Creating a dialog and showing it */
        builder.create().show()
    }
    
}
