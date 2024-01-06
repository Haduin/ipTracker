package org.wit.iptracker;

public record IpApiResponse(
        String query,
        String country,
        String city
) {

}
