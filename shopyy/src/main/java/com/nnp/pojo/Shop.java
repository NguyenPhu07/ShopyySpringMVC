/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "shop")
@NamedQueries({
    @NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s"),
    @NamedQuery(name = "Shop.findById", query = "SELECT s FROM Shop s WHERE s.id = :id"),
    @NamedQuery(name = "Shop.findByName", query = "SELECT s FROM Shop s WHERE s.name = :name"),
    @NamedQuery(name = "Shop.findByPhone", query = "SELECT s FROM Shop s WHERE s.phone = :phone"),
    @NamedQuery(name = "Shop.findByAddress", query = "SELECT s FROM Shop s WHERE s.address = :address"),
    @NamedQuery(name = "Shop.findByActive", query = "SELECT s FROM Shop s WHERE s.active = :active"),
    @NamedQuery(name = "Shop.findByImage", query = "SELECT s FROM Shop s WHERE s.image = :image")})
@Component
public class Shop implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Set<OrderDetail> orderDetailSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Column(name = "active")
    private Boolean active;
    @Size(max = 200)
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "shopId")
    private Set<Product> productSet;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(mappedBy = "shopId")
    private Set<Category> categorySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Set<CommentShop> commentShopSet;

    public Shop() {
    }

    public Shop(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public Set<CommentShop> getCommentShopSet() {
        return commentShopSet;
    }

    public void setCommentShopSet(Set<CommentShop> commentShopSet) {
        this.commentShopSet = commentShopSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnp.pojo.Shop[ id=" + id + " ]";
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }
    
}
