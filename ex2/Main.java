class Main {
    static ImList<Service> serveCruisesRecycle(ImList<Cruise> cruises) {
        ImList<Service> activeServices = new ImList<Service>();
        ImList<Service> expiredServices = new ImList<Service>();
        ImList<Service> logServices = new ImList<Service>();
        int loaderNum = 1;
        for (Cruise cruise : cruises) {
            // check if each active service has expired, by checking whether the arrival
            // time of
            // cruise is after the end time of the service
            int i = 0;
            int last = activeServices.size() - 1;
            while (i <= last) {
                Service service = activeServices.get(i);
                if (service.getServiceEndTime() <= cruise.getArrivalTime()) {
                    expiredServices = new ImList<Service>().addAll(expiredServices).add(service);
                    activeServices = new ImList<Service>().addAll(activeServices).remove(i);
                    last--;
                } else {
                    i++;
                }
            }

            // System.out.println("Cruise arrived at " + cruise.getArrivalTime() + " Service
            // Time " + cruise.getServiceTime() + " needs " +
            // cruise.getNumOfLoadersRequired());
            // System.out.println("Active services");
            // System.out.println(activeServices);
            // System.out.println("Expired services");
            // System.out.println(expiredServices);

            // deploy loaders to serve the cruise
            for (int j = 0; j < cruise.getNumOfLoadersRequired(); j++) {
                // if there are services available, get the first available one, shift to active
                // services
                Service service;
                if (!expiredServices.isEmpty()) {
                    service = expiredServices.get(0).serveNewCruise(cruise);
                    expiredServices = new ImList<Service>()
                            .addAll(expiredServices)
                            .remove(0);
                } else {
                    Loader loader = new Loader(loaderNum);
                    if (loaderNum % 3 == 0) {
                        service = new RecycleService(loader, cruise);
                    } else {
                        service = new Service(loader, cruise);
                    }
                    loaderNum++;
                }
                activeServices = new ImList<Service>()
                        .addAll(activeServices)
                        .add(service);
                logServices = new ImList<Service>()
                        .addAll(logServices)
                        .add(service);
            }
        }
        return logServices;
    }

    static ImList<Service> serveCruises(ImList<Cruise> cruises) {
        ImList<Service> activeServices = new ImList<Service>();
        ImList<Service> expiredServices = new ImList<Service>();
        ImList<Service> logServices = new ImList<Service>();
        int loaderNum = 1;
        for (Cruise cruise : cruises) {
            // check if each active service has expired, by checking whether the arrival
            // time of
            // cruise is after the end time of the service
            int i = 0;
            int last = activeServices.size() - 1;
            while (i <= last) {
                Service service = activeServices.get(i);
                if (service.getServiceEndTime() <= cruise.getArrivalTime()) {
                    expiredServices = new ImList<Service>().addAll(expiredServices).add(service);
                    activeServices = new ImList<Service>().addAll(activeServices).remove(i);
                    last--;
                } else {
                    i++;
                }
            }

            // System.out.println("Cruise arrived at " + cruise.getArrivalTime() + " Service
            // Time " + cruise.getServiceTime() + " needs " +
            // cruise.getNumOfLoadersRequired());
            // System.out.println("Active services");
            // System.out.println(activeServices);
            // System.out.println("Expired services");
            // System.out.println(expiredServices);

            // deploy loaders to serve the cruise
            for (int j = 0; j < cruise.getNumOfLoadersRequired(); j++) {
                // if there are services available, get the first available one, shift to active
                // services
                Service service;
                if (!expiredServices.isEmpty()) {
                    service = expiredServices.get(0).serveNewCruise(cruise);
                    expiredServices = new ImList<Service>()
                            .addAll(expiredServices)
                            .remove(0);
                } else {
                    Loader loader = new Loader(loaderNum++);
                    service = new Service(loader, cruise);
                }
                activeServices = new ImList<Service>()
                        .addAll(activeServices)
                        .add(service);
                logServices = new ImList<Service>()
                        .addAll(logServices)
                        .add(service);
            }
        }
        return logServices;
    }

    public static void main(String[] args) {
        // System.out.println("--- LEVEL 1 ---");
        // System.out.println(new Cruise("A1234", 0, 2, 30));
        // System.out.println(new Cruise("A2345", 30, 2, 30));
        // System.out.println(new Cruise("A3456", 130, 2, 30));
        // System.out.println(new Cruise("A3456", 130, 2, 30).getArrivalTime());
        // System.out.println(new Cruise("A3456", 130, 2,
        // 30).getNumOfLoadersRequired());
        // System.out.println(new Cruise("A3456", 130, 5,
        // 30).getNumOfLoadersRequired());
        // System.out.println(new Cruise("A1234", 0, 2, 30).getServiceTime());
        // System.out.println(new Cruise("A1234", 0, 2, 45).getServiceTime());
        // System.out.println(new Cruise("CS2030", 1200, 2, 100).getServiceTime());

        // System.out.println("--- LEVEL 2 ---");
        // System.out.println(new Service(new Loader(1), new Cruise("A1234", 0, 2,
        // 30)).getServiceStartTime());
        // System.out.println(new Service(new Loader(1), new Cruise("A1234", 0, 2,
        // 30)).getServiceEndTime());
        // System.out.println(new Service(new Loader(2), new Cruise("A3456", 130, 2,
        // 30)));
        // System.out.println(new Service(new Loader(2), new Cruise("A3456", 130, 2,
        // 30)).getServiceStartTime());
        // System.out.println(new Service(new Loader(2), new Cruise("A3456", 130, 2,
        // 30)).getServiceEndTime());

        // System.out.println("--- LEVEL 3 ---");
        // ImList<Cruise> cruises = new ImList<Cruise>()
        // .add(new Cruise("A1234", 0, 2, 30))
        // .add(new Cruise("A2345", 30, 2, 30))
        // .add(new Cruise("A3456", 130, 2, 30));
        // System.out.println(cruises);
        // for (Service s : serveCruises(cruises)) {
        // System.out.println(s);
        // }
        // cruises = new ImList<Cruise>()
        // .add(new Cruise("A1234", 0, 2, 30))
        // .add(new Cruise("A2345", 0, 2, 10))
        // .add(new Cruise("A3456", 10, 2, 20));
        // System.out.println(cruises);
        // for (Service s : serveCruises(cruises)) {
        // System.out.println(s);
        // }

        // System.out.println("--- LEVEL 4 ---");
        // ImList<Cruise> cruises = new ImList<Cruise>().add(new SmallCruise("S1111",
        // 1300));
        // System.out.println(cruises);
        // System.out.println(serveCruises(cruises));
        // cruises = new ImList<Cruise>()
        // .add(new BigCruise("B1111", 1300, 80, 3000))
        // .add(new SmallCruise("S1111", 1359))
        // .add(new SmallCruise("S1112", 1500))
        // .add(new SmallCruise("S1113", 1529));
        // System.out.println(cruises);
        // for (Service s : serveCruises(cruises)) {
        // System.out.println(s);
        // }
        // cruises = new ImList<Cruise>()
        // .add(new SmallCruise("S1111", 900))
        // .add(new BigCruise("B1112", 901, 100, 1))
        // .add(new BigCruise("B1113", 902, 20, 4500))
        // .add(new SmallCruise("S2030", 1031))
        // .add(new BigCruise("B0001", 1100, 30, 1500))
        // .add(new SmallCruise("S0001", 1130));
        // System.out.println(cruises);
        // for (Service s : serveCruises(cruises)) {
        // System.out.println(s);
        // }

        System.out.println("--- LEVEL 5 ---");
        ImList<Cruise> cruises = new ImList<Cruise>()
                .add(new BigCruise("B1111", 0, 60, 1500))
                .add(new SmallCruise("S1112", 0))
                .add(new BigCruise("B1113", 30, 100, 1500))
                .add(new BigCruise("B1114", 100, 100, 1500))
                .add(new BigCruise("B1115", 130, 100, 1500))
                .add(new BigCruise("B1116", 200, 100, 1500));
        for (Service s : serveCruisesRecycle(cruises)) {
            System.out.println(s);
        }
    }
}
