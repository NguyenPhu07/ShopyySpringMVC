/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.repository.impl;

import com.nnp.pojo.Product;
import com.nnp.pojo.Shop;
import com.nnp.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
//import javax.persistence.Query;
import org.hibernate.query.Query;// dùng query của hibernate nó nhiều hơn, đa dạng hơn của javax
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class ProductRepositoryImpl implements ProductRepository {

    private static final int PAGE_SIZE = 7;

    @Autowired
    private LocalSessionFactoryBean factory;

    //Hàm Phân Trang
    public List<Product> pagination(Session s, Query q, String page) {
        int p = 1;
        // phan trang
        if (page != null && !page.isEmpty()) {
            p = Integer.parseInt(page);
        }

        int start = (p - 1) * PAGE_SIZE;

        q.setMaxResults(PAGE_SIZE);
        q.setFirstResult(start);

        return q.getResultList();
    }

    @Override
    public Product getProdById(int productId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query que = s.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
        que.setParameter("id", productId);

        return (Product) que.getSingleResult();
    }

    @Override
    public List<Product> getProsByName(String keyword) {
        // %keyword% để tìm bất cứ nội dung gì theo tên, manufacture, description
        // like lower để chuyển về chuỗi thường
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT p FROM Product p " // phải có select khi join nhau 
                + "JOIN p.categoryId c "
                + "WHERE LOWER(p.name) LIKE LOWER(:keyword) "
                + "OR LOWER(p.description) LIKE LOWER(:keyword) "
                + "OR LOWER(p.manufacture) LIKE LOWER(:keyword) "
                + "OR LOWER(c.name) LIKE LOWER(:keyword)";

        Query<Product> query = s.createQuery(hql, Product.class);
        query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");

        return query.getResultList();
    }

    @Override
    public List<Product> getProds(String page) {
        Session s = this.factory.getObject().getCurrentSession();
        Query que = s.createQuery("From Product", Product.class);

        return pagination(s, que, page);
    }

//    @Override
//    public Product getProsById(int productId) {
//        try ( Session s = HibernateUtils.getFactory().openSession()) {
//            return s.get(Product.class, productId);
//        }
//    }
    @Override
    public List<Product> getProsByCate(int categoryId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = builder.createQuery(Product.class);
        Root root = q.from(Product.class);

        Predicate p4 = builder.equal(root.get("categoryId"), categoryId);
        // CHỖ SO SÁNH NÀY CÓ VẤN NẾU THÊM .as(Integer.class) thì nó dell hiểu => nên xóa xóa .as chỗ categoryId

        q.where(p4);

        Query que = s.createQuery(q);

        return que.getResultList();
    }

    @Override
    public List<Product> getProsGreaterThanByPrice(Map<String, String> params, String page) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = builder.createQuery(Product.class);
        Root root = q.from(Product.class);

        if (params != null) {
            String price = params.get("price");
            if (price != null && !price.isEmpty()) {
                Predicate p1 = builder.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(price));
                q.where(p1);
            }
        } else {// nếu chưa có params truyền vào!
            q.select(root);// thì lấy hết
        }

        Query que = s.createQuery(q);

        return pagination(s, que, page);
    }

    @Override
    public List<Product> getProsLessThanByPrice(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = builder.createQuery(Product.class);
        Root root = q.from(Product.class);

        if (params != null) {
            String price = params.get("price");
            if (price != null && !price.isEmpty()) {
                Predicate p1 = builder.lessThanOrEqualTo(root.get("price"), Double.parseDouble(price));
                q.where(p1);
            }
        } else {// nếu chưa có params truyền vào!
            q.select(root);// thì lấy hết
        }

        Query que = s.createQuery(q);

        return que.getResultList();
    }

    //--shop-----------------
    @Override
    public List<Product> getProsByShop(int shopId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = builder.createQuery(Product.class);
        Root root = q.from(Product.class);

        Predicate p4 = builder.equal(root.get("shopId"), shopId);
        // CHỖ SO SÁNH NÀY CÓ VẤN NẾU THÊM .as(Integer.class) thì nó dell hiểu => nên xóa xóa .as chỗ categoryId

        q.where(p4);

        Query que = s.createQuery(q);

        return que.getResultList();

    }

    // Lọc theo giá tối thiểu
    // Lọc sản phẩm theo giá tối đa
    @Override
    // Lọc sản phẩm theo giá tối thiểu
    public List<Product> filterProductsGreaterThanInShop(List<Product> products, double price) {
        List<Product> filteredProducts = new ArrayList<Product>();

        for (Product product : products) {
            if (product.getPrice() >= price) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    // Lọc sản phẩm theo giá tối đa
    @Override
    public List<Product> filterProductsLessThanInShop(List<Product> products, double price) {
        List<Product> filteredProducts = new ArrayList<Product>();

        for (Product product : products) {
            if (product.getPrice() <= price) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    // Lọc sản phẩm theo một danh mục cụ thể
    @Override
    public List<Product> filterProductsByCateIdInShop(List<Product> products, int cateId) {
        List<Product> filteredProducts = new ArrayList<Product>();

        for (Product product : products) {
            if (product.getCategoryId().getId().equals(cateId)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    // kiểm tra trường name vs fk shopId có trùng ko, description đell quan tâm
    public boolean isExistProduct(String prodName, Shop shopId) {// kiểm tra sự tồn tại dể tránh trùng lắp
        Session s = this.factory.getObject().getCurrentSession();

        TypedQuery<Long> que = s.createQuery("SELECT COUNT(p) FROM Product p WHERE p.name =:prodName AND p.shopId =: shopId", Long.class);
        que.setParameter("prodName", prodName);
        que.setParameter("shopId", shopId);
        long count = (long) que.getSingleResult();

        return count > 0;

    }

    @Override
    public Boolean AddorUpdate(Product p) { // phai set product xong ms bo do AddorUpdate
        Session s = this.factory.getObject().getCurrentSession();

        if (p.getId() != null) { // đã có sản phẩm rồi chỉ cập nhật thui nhờ form: hidden path=id đã có đối tượng ở backing bean rồi!
            s.update(p);

        } else { // chưa có sản phẩm nào, thêm mới
            // kiểm tra trùng lắp khi tạo mới
            if (isExistProduct(p.getName(), p.getShopId())) {
                return false;
            }
            s.save(p);
        }

        return true;
    }

    @Override
    public Boolean DeleteProduct(Product p) {
        Session s = this.factory.getObject().getCurrentSession();
        // kiểm tra có tồn tại hay ko nếu có thì xóa ko thì cút
        if (isExistProduct(p.getName(), p.getShopId()) == true) {// nếu có tồn tại thì xóa được
            s.remove(p);
            return true;
        } else {
            return false; // nếu ko tồn tại thì ko được
        }

    }

}
