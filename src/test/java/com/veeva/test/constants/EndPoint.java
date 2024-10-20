/*
 * Project: Nagender Test Project For Veeva
 * Author: Nagender Bojjawar
 * Date: 2024-10-18
 */

package com.veeva.test.constants;

public enum EndPoint {
    COREPRODUCT("/warriors"),
    DERIVEDPRODUCT1("/sixers"),
    DERIVEDPRODUCT2("/bulls");

    public final String url;

    EndPoint(String url) {
        this.url = url;
    }
}
