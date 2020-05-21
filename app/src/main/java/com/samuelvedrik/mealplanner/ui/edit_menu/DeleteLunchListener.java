package com.samuelvedrik.mealplanner.ui.edit_menu;

public class DeleteLunchListener implements DeleteListener {

    private EditMenuPresenter presenter;

    public DeleteLunchListener(EditMenuPresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void notifyDelete(int position) {
        presenter.removeLunch(position);
    }
}
