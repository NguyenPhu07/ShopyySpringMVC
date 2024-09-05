/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 *
 * @author Admin
 */
public class DispathcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {
     @Override // kế thừa lớp trừu tượng or ko kế thừa
    protected Class<?>[] getRootConfigClasses() {
       return new Class[]{
           HibernateConfigs.class,
           TilesConfigs.class,
           SecurityConfigs.class
       };
    }

    @Override// kế thừa lớp interface
    protected Class<?>[] getServletConfigClasses() {
         return new Class[] {
            WebAppContextConfigs.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
