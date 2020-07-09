package com.example.watched.util;

/**
 * This generic interface is used as custom callback for async tasks.
 * For an example usage see {@link com.example.watched.ui.mgmt.RegisterActivity:75}.
 */
public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
