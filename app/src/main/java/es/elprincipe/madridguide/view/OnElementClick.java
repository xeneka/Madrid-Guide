package es.elprincipe.madridguide.view;


public interface OnElementClick<T> {

    public abstract void clickedOn(T shop, int position);

}
