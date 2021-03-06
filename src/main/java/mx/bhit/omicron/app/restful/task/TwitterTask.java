/**
 * TwitterTask.java
 * Fecha de creación: 08/06/2016, 13:45:33
 *
 * Copyright (c) 2016 Instituto Federal Electoral. Dirección
 * Ejecutiva del Registro Federal de Electores.
 * Periférico Sur 239, México, D.F., C.P. 01010.
 * Todos los derechos reservados.
 *
 * Este software es información confidencial, propiedad del
 * Instituto Federal Electoral. Esta información confidencial
 * no deberá ser divulgada y solo se podrá utilizar de acuerdo
 * a los términos que determine el propio Instituto.
 */

package mx.bhit.omicron.app.restful.task;

import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.bhit.omicron.app.restful.controller.Trigger;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoredData;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @since SIIRFE 6.0
 */
public class TwitterTask extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(TwitterTask.class);

    /**
     * TODO [Agregar documentación del atributo]
     */
    public static ExecutorService executor;

    // private TwitterMonitorThread twitterMonitorThread;
    private Callable<?> thread;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public TwitterTask() {
        // TODO [codificar el cuerpo del método]
        executor = Executors.newCachedThreadPool();
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param thread
     */
    public void addThread(Callable<?> thread) {
        this.thread = thread;
    }

    /* La documentación de este método se encuentra en la clase o interface que
     * lo declara (non-Javadoc)
     * @see java.util.TimerTask#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        // TODO [codificar el cuerpo del método]
        if (thread != null) {
            if (!executor.isShutdown()) {
                executor.submit(thread);
            }
        } else {
            logger.info("No Thread set.");
        }
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public static void stop() {
        logger.info("Apagando hilo");
        Trigger.stopMonitoreoTopicos();
    }

}
