package com.mibaldi.loanmanagement.base.baseMosby.fragment;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.mibaldi.loanmanagement.base.BasePresenter;
import com.mibaldi.loanmanagement.base.BaseView;
import com.mibaldi.loanmanagement.di.HasComponent;

/**
 * BaseFragment with mosby
 */
public abstract class BaseMVPFragment<P extends BasePresenter<V>, V extends BaseView> extends MvpFragment<V, P> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
