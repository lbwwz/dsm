package com.dsm.service.impls;

import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.service.interfaces.IProductService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/27
 *
 * @author : Lbwwz
 */
public class ProductServiceImpl implements IProductService {

    @Transactional
    @Override
    public BackMsg releaseProduct(ReleaseProductFormDTO dto) {

        return null;
    }
}
