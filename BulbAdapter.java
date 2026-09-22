public class BulbAdapter implements SmartDevice {
    private final LegacyBulb bulb;
    private final int k = 9;

    public BulbAdapter(LegacyBulb bulb) {
        if (bulb == null) {
            throw new IllegalArgumentException("Bulb cannot be null");
        }
        this.bulb = bulb;
    }

    @Override
    public void turnOn() {
        bulb.setBrightness(255);
    }

    @Override
    public void turnOff() {
        bulb.setBrightness(0);
    }

    @Override
    public boolean isOn() {
        return bulb.hasPower() && bulb.readBrightness() > 0;
    }

    @Override
    public int getPowerPercent() {
        if (!bulb.hasPower() || bulb.readBrightness() == 0) {
            return 0;
        }

        int rawPercent = (bulb.readBrightness() * 100) / 255;
        int result = rawPercent + k;

        if (result > 100) {
            return 100;
        }
        return result;
    }
}