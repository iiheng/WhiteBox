// IBPackageInstallerService.aidl
package com.vcamx.whitebox.server.pm;

import com.vcamx.whitebox.server.pm.WPackageSettings;
import com.vcamx.whitebox.entity.pm.InstallOption;

// Declare any non-default types here with import statements

interface IWPackageInstallerService {
    int installPackageAsUser(in WPackageSettings file, int userId);
    int uninstallPackageAsUser(in WPackageSettings file, boolean removeApp, int userId);
    int updatePackage(in WPackageSettings file);
}
