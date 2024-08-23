/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "comment_shop")
@NamedQueries({
    @NamedQuery(name = "CommentShop.findAll", query = "SELECT c FROM CommentShop c"),
    @NamedQuery(name = "CommentShop.findById", query = "SELECT c FROM CommentShop c WHERE c.id = :id"),
    @NamedQuery(name = "CommentShop.findByComment", query = "SELECT c FROM CommentShop c WHERE c.comment = :comment"),
    @NamedQuery(name = "CommentShop.findByRating", query = "SELECT c FROM CommentShop c WHERE c.rating = :rating"),
    @NamedQuery(name = "CommentShop.findByCreatedDate", query = "SELECT c FROM CommentShop c WHERE c.createdDate = :createdDate")})
public class CommentShop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "comment")
    private String comment;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shop shopId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public CommentShop() {
    }

    public CommentShop(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Shop getShopId() {
        return shopId;
    }

    public void setShopId(Shop shopId) {
        this.shopId = shopId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof CommentShop)) {
            return false;
        }
        CommentShop other = (CommentShop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nnp.pojo.CommentShop[ id=" + id + " ]";
    }
    
}
