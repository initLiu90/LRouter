package com.lzp.router.api.logistics;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;

import com.lzp.router.api.exception.HandlerException;
import com.lzp.router.api.utils.RLog;
import com.lzp.router.api.utils.RouterUtils;
import com.lzp.router.api.utils.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Router配置类
 * 配置path，uri，requestCode 启动参数等
 */
public class RouterBuilder {
    private static final String TAG = RouterBuilder.class.getSimpleName();

    Bundle extras;
    int requestCode = -1;
    String path;
    Uri uri;
    int flags;
    Context context;
    List<Interceptor> interceptors;

    public RouterBuilder(Context context, String path) {
        this.context = context;

        this.path = path;
        this.uri = null;
    }

    public RouterBuilder(Context context, Uri uri) {
        if (uri == null || TextUtils.isEmpty(uri.toString()))
            throw new HandlerException("param uri is invalid");

        this.context = context;

        this.uri = uri;
        this.path = uri.getPath();

        ArrayMap<String, String> querys = RouterUtils.extractQuery(uri.getQuery());
        RLog.i(TAG, "Uri query params=" + querys.toString());

        for (Map.Entry<String, String> entry : querys.entrySet()) {
            putExtra(entry.getKey(), entry.getValue());
        }
    }


    public RouterBuilder putExtra(String name, boolean value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        extras.putBoolean(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The byte data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, byte value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putByte(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The char data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, char value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putChar(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The short data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, short value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putShort(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The integer data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, int value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putInt(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The long data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, long value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putLong(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The float data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, float value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putFloat(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The double data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, double value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putDouble(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The String data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, String value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putString(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The CharSequence data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, CharSequence value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putCharSequence(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Parcelable data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, Parcelable value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putParcelable(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Parcelable[] data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, Parcelable[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putParcelableArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The ArrayList<Parcelable> data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putParcelableArrayListExtra(String name,
                                                     ArrayList<? extends Parcelable> value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putParcelableArrayList(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The ArrayList<Integer> data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putIntegerArrayListExtra(String name, ArrayList<Integer> value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putIntegerArrayList(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The ArrayList<String> data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putStringArrayListExtra(String name, ArrayList<String> value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putStringArrayList(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The ArrayList<CharSequence> data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putCharSequenceArrayListExtra(String name,
                                                       ArrayList<CharSequence> value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putCharSequenceArrayList(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Serializable data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, Serializable value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putSerializable(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The boolean array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, boolean[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putBooleanArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The byte array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, byte[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putByteArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The short array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, short[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putShortArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The char array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, char[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putCharArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The int array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, int[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putIntArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The byte array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, long[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putLongArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The float array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, float[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putFloatArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The double array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, double[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putDoubleArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The String array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, String[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putStringArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The CharSequence array data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, CharSequence[] value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putCharSequenceArray(name, value);
        return this;
    }

    /**
     * Add extended data to the intent.  The name must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param name  The name of the extra data, with package prefix.
     * @param value The Bundle data value.
     * @return Returns the same Intent object, for chaining multiple calls
     * into a single statement.
     * @see #putExtras
     */
    public RouterBuilder putExtra(String name, Bundle value) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putBundle(name, value);
        return this;
    }

    /**
     * Copy all extras in 'src' in to this intent.
     *
     * @param src Contains the extras to copy.
     * @see #putExtra
     */
    public RouterBuilder putExtras(Intent src) {
        if (src.getExtras() != null) {
            if (this.extras == null) {
                this.extras = new Bundle(src.getExtras());
            } else {
                this.extras.putAll(src.getExtras());
            }
        }
        return this;
    }

    /**
     * Add a set of extended data to the intent.  The keys must include a package
     * prefix, for example the app com.android.contacts would use names
     * like "com.android.contacts.ShowAll".
     *
     * @param extras The Bundle of extras to add to this intent.
     * @see #putExtra
     */
    public RouterBuilder putExtras(Bundle extras) {
        if (this.extras == null) {
            this.extras = new Bundle();
        }
        this.extras.putAll(extras);
        return this;
    }

    public RouterBuilder setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    /**
     * @param flag Intent flag
     * @return
     */
    public RouterBuilder setFlag(int flag) {
        this.flags = flag;
        return this;
    }

    /**
     * @param flags Intent flag
     * @return
     */
    public RouterBuilder addFlag(int flags) {
        this.flags |= flags;
        return this;
    }

    public RouterBuilder addInterceptor(Interceptor interceptor) {
        if (this.interceptors == null) {
            this.interceptors = new ArrayList<>();
        }
        this.interceptors.add(interceptor);
        return this;
    }

    public Router create() {
        return new Router(this);
    }
}
