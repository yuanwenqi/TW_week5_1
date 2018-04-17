package practice1;

import com.google.common.collect.ImmutableList;

public class Security {

    private SecurityChecker securityChecker;

    public Security(SecurityChecker checker) {
        this.securityChecker = checker;
    }

    public boolean hasAccess(User user, Permission permission, ImmutableList<Permission> permissions) {

        boolean isAccess = false;

        if (permissions.size() == 0) {
            return isAccess;
        }

        if (user == null | permission == null ) {
            return isAccess;
        }


        if (securityChecker.isAdmin()) {
            isAccess = true;
        }

        if (this.securityChecker.checkPermission(user, permission) ) {
            isAccess = true;
        }

        if (permissions.contains(permission)) {
            isAccess = true;
        }
        return isAccess;
    }
}
