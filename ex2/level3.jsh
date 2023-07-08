ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> activeServices = new ImList<Service>();
    ImList<Service> expiredServices = new ImList<Service>();
    ImList<Service> logServices = new ImList<Service>();
    int loaderNum = 1;
    for (Cruise cruise : cruises) {
        // check if each active service has expired, by checking whether the arrival time of
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

        // deploy loaders to serve the cruise
        for (int j = 0; j < cruise.getNumOfLoadersRequired(); j++) {
            // if there are services available, get the first available one, shift to active services
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
