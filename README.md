# Water-Points

This application to demonstrate a data processing module which takes a dataset URL as input and returns:

1. The number of water points that are functional
2. The number of water points per community
3. The rank for each community by the percentage of broken water points

Algorithm

1. Create general Processor class
2. Each of above return, create one class inherits from Processor class:  CounterWaterPoint, CommunityWaterPoint, FunctionalWaterPoint, RankCommunityWaterPoint
3. Create ProcessorManager class as container for created point 2 class.
4. Create main() to run all the processes:


        public static void main(String[] args) {
        // create manager
        ProcessorManager manager = new ProcessorManager();

        // register data processors - can add more...
        manager.register(new CounterWaterPoint());
        manager.register(new FunctionalWaterPoint());
        manager.register(new CommunityWaterPoint());
        manager.register(new RankCommunityWaterPoint());

        // run
        if (!manager.run(URL_String)) {
            System.out.println("No Result...");
            return;
        }

        // success no error
        System.out.println("----------------------- JSON Raw -----------------------------");
        System.out.println(manager.getResult());

        System.out.println("\n\n----------------------- JSON Format -----------------------------");
        System.out.println(JsonFormatter.format(manager.getResult()));
        }
  
  5. Unit Test included with JUnit
