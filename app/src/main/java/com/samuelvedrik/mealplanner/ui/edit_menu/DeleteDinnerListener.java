package com.samuelvedrik.mealplanner.ui.edit_menu;

public class DeleteDinnerListener implements DeleteListener {
    private EditMenuPresenter presenter;

    public DeleteDinnerListener(EditMenuPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notifyDelete(int position) {
        presenter.removeDinner(position);
    }
}
