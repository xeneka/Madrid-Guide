package es.elprincipe.madridguide.model;

/**
 * Created by Antonio on 12/12/16.
 */
public interface IShopsUdatable {

    void add(Shop shop);
    void delete(Shop shop);
    void edit(Shop newShop, long index);

}
