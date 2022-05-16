package winnerpov.features.command

import java.util.ArrayList

/**
 * @version     10.0-Warshaw
 * @author      GitHub : mjaucher
 */

abstract class AbstractCommand(val name : String)
{
    abstract fun onCommand(Value : ArrayList<Double>)
}