package com.planck.onlineshop;

import com.planck.DAO.SaleDetailDAO;
import com.planck.Model.SaleDetail;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/saleDetails")
public class SaleDetailController {
    SaleDetailDAO saleDetailDAO = new SaleDetailDAO();

    @RequestMapping("/list")
    public ArrayList<SaleDetail> listSaleDetails() {
        return saleDetailDAO.listSaleDetails();
    }

    @PostMapping("/create")
    public SaleDetail createSaleDetail(@RequestBody SaleDetail sale) {
        saleDetailDAO.createSaleDetail(sale);
        return sale;
    }

    @GetMapping("/lastId")
    public long getLastSaleId() {
        return saleDetailDAO.getLastSaleDetailId();
    }


}
