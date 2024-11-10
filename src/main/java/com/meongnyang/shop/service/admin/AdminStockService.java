package com.meongnyang.shop.service.admin;

import com.meongnyang.shop.dto.request.admin.ReqModifyStockListDto;
import com.meongnyang.shop.dto.request.admin.ReqStockSearchOptionDto;
import com.meongnyang.shop.dto.response.admin.RespGetStocksDto;
import com.meongnyang.shop.entity.Stock;
import com.meongnyang.shop.repository.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableScheduling
@Service
public class AdminStockService {

    @Autowired
    private StockMapper stockMapper;

    public RespGetStocksDto getStocks() {
        List<Stock> stockList = stockMapper.findStockAll();
        return RespGetStocksDto.builder()
                .stockList(stockList)
                .stockListCount(stockList.size())
                .build();
    }

    public RespGetStocksDto getProductsStockByOption(ReqStockSearchOptionDto dto) {
        List<Stock> stockList = stockMapper.findStockByOption(
                dto.getOption(),
                dto.getSearch(),
                (dto.getPage() - 1) * dto.getLimit(),
                dto.getLimit()
        );
        System.out.println(dto);
        return RespGetStocksDto.builder()
                .stockList(stockList)
                .stockListCount(stockMapper.getAllCount(dto.getOption(), dto.getSearch(), (dto.getPage() - 1) * dto.getLimit(), dto.getLimit()))
                .build();
    }

    public void modifyStock(ReqModifyStockListDto dto) {
        List<Stock> stockList = dto.getModifyStockList();
        for(Stock stock : stockList) {
            stockMapper.modifyStockById(stock);
        }
    }

    // 매일 자정에 실행됨
    @Scheduled(cron = "0 0 0 * * *")
    public void changeExpectedStock() {

    }
}