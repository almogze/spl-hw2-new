package bgu.spl.mics.application.services;

import bgu.spl.mics.MessageBus;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.BombDestroyerEvent;
import bgu.spl.mics.application.messages.ExplotionBroadcast;

/**
 * LandoMicroservice
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LandoMicroservice  extends MicroService {

    private final long duration;
    public LandoMicroservice(long duration) {
        super("Lando");
        this.duration = duration;
    }

    @Override
    protected void initialize() {
        subscribeBroadcast(ExplotionBroadcast.class, (exp) -> {diary.setLandoTerminate(System.currentTimeMillis());
            terminate();});
        subscribeEvent(BombDestroyerEvent.class,(bombardment) -> {
            try{
                Thread.sleep(duration);
            }catch (InterruptedException e){ System.out.println("Sleep had fail"); }

            complete(bombardment, true);
        });
    }

}
