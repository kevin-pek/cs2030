void findBestBooking(Request req, List<Driver> drivers) {
    ImList<Booking> bookings = new ImList<>();
    for (int i = 0; i < drivers.size(); i++) {
        Driver driver = drivers.get(i);
        for (int j = 0; j < driver.getServices().size(); j++) {
            Service service = driver.getServices().get(j);
            bookings = new ImList<Booking>().addAll(bookings)
                    .add(new Booking(driver, req, service));
        }
    }
    bookings = bookings.sort(new Comparator<Booking>() {
        @Override
        public int compare(Booking some, Booking other) {
            if (some.getBestFare() == other.getBestFare()) {
                return some.getWaitingTime() - other.getWaitingTime();
            }
            return some.getBestFare() - other.getBestFare();
        }
    });
    for (int i = 0; i < bookings.size(); i++) {
        System.out.println(bookings.get(i));
    }
}
