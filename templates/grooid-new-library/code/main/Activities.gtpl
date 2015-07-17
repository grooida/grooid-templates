package ${defaultPackage}

import android.app.Activity
import android.content.Intent
import com.arasthel.swissknife.annotations.OnUIThread
import com.arasthel.swissknife.dsl.AndroidDSL

/**
 * This is an example on how you can create useful extensions with Groovy and add functionality to your
 * activities easily.
 */
final class Activities {

    /**
     * This extension will make possible to add a new activity passing an Extra serializable object to the activity
     * we want to start:
     *
     * <pre>
     *   startActivityWithExtra(DestinationActivity, 'storingKey', serializable)
     * </pre>
     *
     * @param from the current activity
     * @param to the activity we want to launch
     * @param key the key where the extra will be stored
     * @param serializable the object we want to store
     *
     */
    static void startActivityWithExtra(final Activity from, final Class<? extends Activity> to, final String key, final Serializable serializable) {
        Intent intent = new Intent(from, to)
        intent.putExtra(key, serializable)

        from.startActivity(intent)
    }

    /**
     * The same way we are able to pass an Extra serializable value to a given {@link android.app.Activity} we
     * would like to be able to read it.
     *
     * <pre>
     *   startActivityWithExtra(CurrentActivity, MySerializable.class, 'storingKey')
     * </pre>
     *
     * Please notice that in Groovy the ".class" is not neccessary for a class:
     *
     ** <pre>
     *   startActivityWithExtra(CurrentActivity, MySerializable, 'storingKey')
     * </pre>
     *
     * @param from the current activity
     * @param type the type of the object we want to retrieve
     * @param key the key the object was stored with
     * @return the instance stored in the intent
     */
    static <U extends Serializable> U getExtraSerializable(final Activity from, final Class<U> type, final String key) {
        return (U) from.intent.getSerializableExtra(key)
    }

    /**
     * This extensions shows toast messages  using AndroidDSL from Swissknife library. This
     * method takes care of showing the message in the proper UI thread thanks to the
     * @OnUIThread annotation.
     *
     * @param activity the current activity
     * @param message the key of the message
     */
    @OnUIThread
    static void showMessage(final Activity activity, int message) {
        AndroidDSL.showToast(activity, activity.getString(message))
    }

}
