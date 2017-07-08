/**
 * ProductABuilder.java
 * Created by liurenyong at 2013-12-19
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.������ģʽ;

/**
 * 
 * @author liurenyong 2013-12-19
 */
public class ProductABuilder implements Builder{
    
    Product product = new ProductA();

    /* (non-Javadoc)
     * @see design.������ģʽ.Builder#buildPart1()
     */
    @Override
    public void buildPart1() {
        product.setPart1();
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Builder#buildPart2()
     */
    @Override
    public void buildPart2() {
        product.setPart2();
    }

    /* (non-Javadoc)
     * @see design.������ģʽ.Builder#buildPart3()
     */
    @Override
    public void buildPart3() {
        product.setPart3();
    }
    
    //...

    public Product getProduct() {
        return product;
    }
}
