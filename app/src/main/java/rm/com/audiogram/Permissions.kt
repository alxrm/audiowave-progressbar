package rm.com.audiogram

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v7.app.AppCompatActivity

enum class PermissionState { GRANTED, DENIED }

fun AppCompatActivity.reqPermissionsOr(vararg permissions: String, alreadyGranted: () -> Unit) {
	when (checkAllPermissions(*permissions)) {
		true -> alreadyGranted()
		false -> requestPermissions(this, permissions, 0)
	}
}

private fun AppCompatActivity.checkAllPermissions(vararg permissions: String): Boolean =
		permissions.isEmpty() or permissions.all { checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }

fun verifyPermissions(grantResults: IntArray): PermissionState {

	// At least one result must be checked.
	if (grantResults.isEmpty()) {
		return PermissionState.DENIED
	}

	// Verify that each required permission has been granted, otherwise return false.
	grantResults.forEach {
		if (it != PackageManager.PERMISSION_GRANTED) {
			return PermissionState.DENIED
		}
	}

	return PermissionState.GRANTED
}