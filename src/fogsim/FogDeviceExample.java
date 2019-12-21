
        int num_user = 10; // number of cloud users
        Calendar calendar = Calendar.getInstance();
        boolean trace_flag = false; // mean trace events

        // We create a Cloud Simulator object and call the initialize method of it .
        // This creates a basic simulation environment of the whole network.
        // The basic stuff like logger , time events and stuff .
        CloudSim.init(num_user, calendar, trace_flag);


        // creating a ram provisioner , this will be used to allocate and provision ram among the devices .
        // initially I gave it a 4GB memory.
        RamProvisioner provisioner = new RamProvisionerSimple(4024);

        // creating a bandwidth allocater provisoner , this will allocate or provision bandwidth among the devices .
        // initially i gave it a 100000 bandwidth.
        BwProvisioner bwProvisioner = new BwProvisionerSimple(10000);

        // a Processing unit provisioner , this will provision MIPS
        PeProvisioner peProvisioner = new PeProvisionerSimple(5000.0);

        // creating a processing unit and assigning it the processing unit provisioner created above .
        Pe peDevice = new Pe(1,peProvisioner);
        List<Pe> listOfPes = List.of(peDevice);

        // creating a vm scheduler
        VmScheduler vmScheduler = new VmSchedulerSpaceShared(listOfPes);

        // creating a basic level power module , that will give power or voltage .
        PowerModel powerModel = new PowerModelLinear(100,5);

        // creating a basic level power host with the above characteristics assigned to it .
        PowerHost powerHost = new PowerHost(1,provisioner,bwProvisioner,1024,listOfPes,vmScheduler,powerModel);
        List<Host> listOfHost = List.of(powerHost);

        // creating a FogDevice characteristic , this will be assigned to a FogDevice later .
        FogDeviceCharacteristics fogDeviceCharacteristics = new FogDeviceCharacteristics(
                "x64",
                "centOS",
                "XEN",
                powerHost,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0
        );

        List<Storage> storageList = List.of(new HarddriveStorage(1024));

        // Now we create the actual FogDevice .

        FogDevice fogDevice = new FogDevice("BILAL-CAMERA"
                ,fogDeviceCharacteristics
                ,new VmAllocationPolicySimple(listOfHost)
                ,storageList
                ,100
                ,100
                ,100
                ,100
                ,100);

        FogDevice fogDevice2= new FogDevice("SARA-CAMERA"
                ,fogDeviceCharacteristics
                ,new VmAllocationPolicySimple(listOfHost)
                ,storageList
                ,100
                ,100
                ,100
                ,100
                ,100);
