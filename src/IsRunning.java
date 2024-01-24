
import com.sun.jna.platform.win32.User32;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.*;

import com.sun.jna.*;




public class IsRunning {


	/**    
     * Checks if Verito is running. 
	 * If so, Verito is brought to focus.
	 * If not, prints out that Verito is not running.
     * @return boolean of Verito's running status
	 * @throws InterruptedException
     */
	public static boolean running() {

        HWND hwnd = User32.INSTANCE.FindWindow("TscShellContainerClass", null); // window class name

		if (hwnd == null) {

			System.out.println("Verito is not running");
            return false;
		}

		else{

			User32.INSTANCE.ShowWindow(hwnd, 9 ); 	//Normal State

			User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

			if(User32.INSTANCE.GetForegroundWindow() == null){
				System.out.println("Verito not brought to focus.");
				return false;
			}

			char[] foregroundText = new char[512];
			char[] veritoText = new char[512];

			HWND foregroundWindow = User32.INSTANCE.GetForegroundWindow(); // then you can call it!


			User32.INSTANCE.GetWindowText(foregroundWindow, foregroundText, 512);
			User32.INSTANCE.GetWindowText(hwnd, veritoText, 512);

			System.out.println("Foreground window: " + Native.toString(foregroundText));
			System.out.println("Verito Window: " + Native.toString(veritoText));

			if(User32.INSTANCE.GetForegroundWindow().equals(hwnd)){
				System.out.println("Verito was brought to focus successfully.");
				return true;
			}

            return false;

		}

	}

}
