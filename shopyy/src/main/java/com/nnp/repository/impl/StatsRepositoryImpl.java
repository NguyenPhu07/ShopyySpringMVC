/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;

import com.nnp.pojo.Category;
import com.nnp.pojo.OrderDetail;
import com.nnp.pojo.Product;
import com.nnp.pojo.SaleOrder;
import com.nnp.pojo.Shop;
import com.nnp.repository.StatsRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    // thống kê doanh thu sản phẩm theo quý tháng năm
    @Override
    public List<Object[]> statRevenueProdListByPeroid(int year, int time, String peroid) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rD = q.from(OrderDetail.class); // bỏ cái <OrderDetail> là đc
        Join<OrderDetail, Product> product = rD.join("productId");
        Join<OrderDetail, SaleOrder> so = rD.join("orderId");

        q.where(b.equal(b.function("YEAR", Integer.class, so.get("createdDate")), year),
                b.equal(b.function(peroid, Integer.class, so.get("createdDate")), time));
        q.multiselect(
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")), // thời gian lụa chọn(input vào)
                product.get("id"),
                product.get("name"),
                b.sum(b.prod(rD.get("quantity"),
                        rD.get("unitPrice"))));

        // gộp lại
        q.groupBy(// gộp theo năm, tháng or quý, tên sản phẩm
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),
                product.get("id")
        );
        // sắp xếp lại
        q.orderBy(
                b.asc(b.function("YEAR", Integer.class, so.get("createdDate")))
        );

        Query que = s.createQuery(q);

        return que.getResultList();
    }

    // thống kê doanh thu Danh Mục theo quý tháng năm
    @Override
    public List<Object[]> statRevenueCatetByPeroid(int year,String peroid) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);// do ở đây cần truy vấn ra nhiều trường nên cần mảng Object
        // join 4 bảng
        Root od = q.from(OrderDetail.class);
        Join<OrderDetail, Product> prod = od.join("productId");
        Join<Product, Category> cate = prod.join("categoryId");
        Join<OrderDetail, SaleOrder> so = od.join("orderId");

        q.where(b.equal(b.function("YEAR", Integer.class, so.get("createdDate")), year));

        q.multiselect(
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),// thời gian lụa chọn(input vào)
                cate.get("id"),
                cate.get("name"),
                b.sum(b.prod(od.get("quantity"), od.get("unitPrice"))),
                cate.get("shopId")
        );
        q.groupBy( // gộp theo năm, tháng or quý, id danh mục
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),
                cate.get("id")
        );
        q.orderBy(
                b.asc(b.function("YEAR", Integer.class, so.get("createdDate"))),
                b.asc(b.function(peroid, Integer.class, so.get("createdDate"))),
                b.asc(cate.get("id"))
        );

        Query que = s.createQuery(q);

        return que.getResultList();

    }

    // thống kê tần suất bán hàng (đếm số lượng đơn hàng của cửa hàng)
    @Override
    public List<Object[]> statsFrequencySaleShopByPeroid(int year, int time, String peroid) {

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);// do ở đây cần truy vấn ra nhiều trường nên cần mảng Object
        // join 4 bảng
        Root<OrderDetail> od = q.from(OrderDetail.class);// sql: from order_detail 
        // join bảng lại
        Join<OrderDetail, SaleOrder> so = od.join("orderId");
        Join<OrderDetail, Shop> shop = od.join("shopId");

        // từ bảng join này where đk hiển thị theo YEAR,Tháng or Qúy 
        q.where(b.equal(b.function("YEAR", Integer.class, so.get("createdDate")), year),
                b.equal(b.function(peroid, Integer.class, so.get("createdDate")), time));

        q.multiselect(
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),// thời gian lụa chọn(input vào)
                shop.get("id"),
                shop.get("name"),
                b.countDistinct(so.get("id"))// đếm số đơn hàng bán ra theo shop!
        );
        q.groupBy( // gộp theo năm, tháng or quý, id cửa hàng
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),
                shop.get("id")
        );
        q.orderBy(
                b.asc(b.function("YEAR", Integer.class, so.get("createdDate")))
//                b.asc(b.function(peroid, Integer.class, so.get("createdDate"))),
//                b.asc(shop.get("id"))
        );

        Query que = s.createQuery(q);

        return que.getResultList();

    }

    // Thống kê tổng sản phẩm kinh doanh(đếm số lượng sản phẩm đã bán ra trong chi tiết hóa đơn theo cửa hàng)
    @Override
    public List<Object[]> statsTotalProdsShopByPeroid(int year, int time, String peroid) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);// do ở đây cần truy vấn ra nhiều trường nên cần mảng Object
        // join 4 bảng
        Root od = q.from(OrderDetail.class);// sql: from order_detail 
        // join bảng lại
        Join<OrderDetail, SaleOrder> so = od.join("orderId");
        Join<OrderDetail, Shop> shop = od.join("shopId");

        q.where(b.equal(b.function("YEAR", Integer.class, so.get("createdDate")), year),
                 b.equal(b.function(peroid, Integer.class, so.get("createdDate")), time));

        q.multiselect(
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),// thời gian lụa chọn(input vào)
                shop.get("id"),
                shop.get("name"),
                b.sum(od.get("quantity"))// tổng lại số phẩm đã bán ra trong chi tiết đơn hàng theo shop_id!
        );
        q.groupBy( // gộp theo năm, tháng or quý, id cửa hàng
                b.function("YEAR", Integer.class, so.get("createdDate")),
                b.function(peroid, Integer.class, so.get("createdDate")),
                shop.get("id")
        );
        q.orderBy(
                b.asc(b.function("YEAR", Integer.class, so.get("createdDate")))
//                b.asc(b.function(peroid, Integer.class, so.get("createdDate"))),
//                b.asc(shop.get("id"))
        );
        Query que = s.createQuery(q);

        return que.getResultList();

    }

}
