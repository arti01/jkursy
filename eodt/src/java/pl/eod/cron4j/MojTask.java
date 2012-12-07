/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.cron4j;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

/**
 *
 * @author arti01
 */
public class MojTask extends Task {

    @Override
    public void execute(TaskExecutionContext tec) throws RuntimeException {
        System.out.println("arti zadanie");
    }
    
}
