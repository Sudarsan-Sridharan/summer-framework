package com.mikeldpl.summer.core.scanner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class SimpleScanner implements Scanner {

    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final String CLASS_SUFFIX = ".class";

    private ScannerObserver listener;
    private ClassLoader classLoader;

    public SimpleScanner() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void setObserver(ScannerObserver scannerObserver) {
        this.listener = scannerObserver;
    }

    @Override
    public void scan(Package aPackage) {
        String packageName = aPackage.getName();
        try {
            String packagePath = packageName.replace(DOT, SLASH);
            Enumeration<URL> packages = classLoader.getResources(packagePath);
            while (packages.hasMoreElements()) {
                File file = new File(packages.nextElement().getFile());
                if (file.isDirectory()) {
                    resolvePackage(packageName, file);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void resolvePackage(String packageName, File packageFile) throws IOException, ClassNotFoundException {
        for (File file : packageFile.listFiles()) {
            String name = packageName + DOT + file.getName();
            if (file.isDirectory()) {
                resolvePackage(name, file);
            } else if (name.endsWith(CLASS_SUFFIX)) {
                resolveClass(name.substring(0, name.length() - CLASS_SUFFIX.length()));
            }
        }
    }

    private void resolveClass(String name) throws ClassNotFoundException {
        listener.meetsClass(classLoader.loadClass(name));
    }
}
