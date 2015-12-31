package com.zhs.haoyangde.entity;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.zhs.haoyangde.entity.Product;

import com.zhs.haoyangde.entity.ProductDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig productDaoConfig;

    private final ProductDao productDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        productDaoConfig = daoConfigMap.get(ProductDao.class).clone();
        productDaoConfig.initIdentityScope(type);

        productDao = new ProductDao(productDaoConfig, this);

        registerDao(Product.class, productDao);
    }
    
    public void clear() {
        productDaoConfig.getIdentityScope().clear();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

}
