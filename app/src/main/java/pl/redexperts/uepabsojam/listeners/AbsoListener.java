package pl.redexperts.uepabsojam.listeners;


import pl.redexperts.uepabsojam.model.Jam;

public interface AbsoListener {

    public interface OnJamAddListener {
        public void onJamAdd(Jam jam);
    }

}
