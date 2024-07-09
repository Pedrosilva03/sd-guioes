package testes.recurso_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FicheirosImp implements Ficheiros {
    private Map<String, Boolean> ficheiros_using;
    private Map<String, Boolean> ficheiros_modificados; // depois de copia de segurança fica tudo a false
    private Boolean backup;
    private int using;

    private ReentrantLock lock = new ReentrantLock();
    private Condition notUsing = lock.newCondition();
    private Condition doBackup = lock.newCondition();

    public FicheirosImp(Map<String, Boolean> ficheiros_using, Map<String, Boolean> ficheiros_modificados) {
        this.ficheiros_using = ficheiros_using;
        for (String ficheiro : ficheiros_using.keySet()) {
            this.ficheiros_using.put(ficheiro, false);
        }
        this.ficheiros_modificados = ficheiros_modificados;
        for (String ficheiro : ficheiros_modificados.keySet()) {
            this.ficheiros_modificados.put(ficheiro, false);
        }
        this.backup = false;
        this.using = 0;
    }

    public void using(String path) {
        lock.lock();
        try {
            while (this.ficheiros_using.get(path) || this.backup) {
                this.notUsing.await();
            }

            this.ficheiros_using.put(path, true);
            this.using++;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notUsing(String path, boolean modified) {
        this.ficheiros_using.put(path, modified);
        this.using--;
        this.notUsing.signalAll();
        if (this.using == 0) {
            this.doBackup.signal();
        }
    }

    public List<String> startBackup() {
        lock.lock();
        try {
            while (using != 0) {
                this.doBackup.await();
            }
    
            this.backup = true;
    
            List<String> modificados = new ArrayList<String>();
    
            for (String ficheiro : ficheiros_modificados.keySet()) {
                if (ficheiros_modificados.get(ficheiro)) {
                    modificados.add(ficheiro);
                }
            }
    
            for (String ficheiro : ficheiros_modificados.keySet()) {
                if (ficheiros_modificados.get(ficheiro)) {
                    this.ficheiros_modificados.put(ficheiro, false);
                }
            }
    
            this.backup = false;
    
            return modificados;
    
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void endBackup() {
        this.backup = false;
        for (String ficheiro : ficheiros_using.keySet()) {
            this.ficheiros_using.put(ficheiro, false);
        }
        this.notUsing.signalAll();
    }
}
