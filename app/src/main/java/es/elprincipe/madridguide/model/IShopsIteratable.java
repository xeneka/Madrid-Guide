package es.elprincipe.madridguide.model;

import java.util.List;

/**
 * Created by Antonio on 12/12/16.
 */
public interface IShopsIteratable {

    long size();
    Shop get(long index);
    List<Shop> allShops();
}
