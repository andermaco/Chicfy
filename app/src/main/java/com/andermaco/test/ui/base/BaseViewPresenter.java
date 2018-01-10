package com.andermaco.test.ui.base;

import com.andermaco.test.common.ResourceManager;
import com.andermaco.test.common.Router;

/**
 * Created by andermaco@gmail.com on 27/07/17.
 *
 * Abstract base class to be inherited for all presenters.
 */

public abstract class BaseViewPresenter<T extends BaseView> implements BasePresenter {

    private T view;
    protected Router router;
    protected ResourceManager resourceManager;

    public BaseViewPresenter(T view, Router router, ResourceManager resourceManager) {
        this.view = view;
        this.router = router;
        this.resourceManager = resourceManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

}
