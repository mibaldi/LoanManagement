package com.mibaldi.loanmanagement.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mibaldi.loanmanagement.R;
import com.mibaldi.loanmanagement.base.baseMosby.fragment.BaseMVPFragment;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.firstThirdFragment.FirstThirdFragmentPresenter;
import com.mibaldi.loanmanagement.ui.presenters.thirdActivity.ThirdActivityComponent;
import com.mibaldi.loanmanagement.ui.views.thirdActivity.FirstThirdFragmentView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FirstThirdFragment extends BaseMVPFragment<FirstThirdFragmentPresenter,FirstThirdFragmentView> implements FirstThirdFragmentView{
    private ThirdActivityComponent component;
    private Unbinder unbind;


    @Inject
    public FirstThirdFragment() {
        setRetainInstance(true);
    }

    public static FirstThirdFragment newInstance() {
        FirstThirdFragment fragment = new FirstThirdFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        component = getComponent(ThirdActivityComponent.class);
        component.inject(this);
        View view = inflater.inflate(R.layout.fragment_first_third,container,false);
        unbind = ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
    }

    @Override
    public FirstThirdFragmentPresenter createPresenter() {
        return component.firstThirdFragmentPresenter();
    }

    @Override
    public void showMessage(String message) {

    }
    @OnClick(R.id.btn_changefragment)
    public void nextStep(){
        presenter.nextStep();
    }
}
