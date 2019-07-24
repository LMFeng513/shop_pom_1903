package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-13 8:57
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Goods> searchByKey(String keyword) {
        SolrQuery solrQuery =null;
        if (keyword ==null||keyword.trim().equals("")){

            solrQuery =new SolrQuery("*:*");
        }else {
            String str ="gname:%s||ginfo:%s";
            String s =String.format(str,keyword,keyword);
            solrQuery =new SolrQuery(s);
        }
        //设置查询的高亮
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.addHighlightField("gname");

        List<Goods>goods=new ArrayList<>();
        try {
            QueryResponse queryResponse =solrClient.query(solrQuery);

            SolrDocumentList results= queryResponse.getResults();

            Map<String,Map<String ,List<String>>> highlighting =queryResponse.getHighlighting();

            for (SolrDocument document: results){
                Goods good =new Goods();

                int id =Integer.parseInt(document.getFieldValue("id")+"");
                String gname=document.getFieldValue("gname")+"";
                String gimage =document.getFieldValue("gimage")+"";
                BigDecimal gprice =new BigDecimal(document.getFieldValue("gprice")+"");
                int gsave = (int) document.getFieldValue("gsave");


                good.setId(id);
                good.setGname(gname);
                good.setGimage(gimage);
                good.setGprice(gprice);
                good.setGsave(gsave);

                //处理高凉
                if (highlighting.containsKey(id+"")){
                    Map<String ,List<String>>stringListMap =highlighting.get(id+"");

                    if(stringListMap.containsKey("gname")){
                        String highlightGname=stringListMap.get("gname").get(0);

                        good.setGname(highlightGname);
                    }
                }
            goods.add(good);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return goods;
    }

    @Override
    public int addGoods(Goods goods) {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id",goods.getId());
        document.setField("gname",goods.getGname());
        document.setField("ginfo",goods.getGinfo());
        document.setField("gimage",goods.getGimage());
        document.setField("gprice",goods.getGprice());
        document.setField("gsave",goods.getGsave());

        try {
            solrClient.add(document);
            solrClient.commit();
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
