package api.config;

import services.ClientService;
import services.ProjectService;
import services.UserService;
import services.WorkspaceService;

public enum EntityConfiguration {

    USER {
        @Override
        public Class<?> getEntityService() {
            return UserService.class;
        }
    },

    WORKSPACE {
        @Override
        public Class<?> getEntityService() {
            return WorkspaceService.class;
        }
    },
    CLIENT {
        @Override
        public Class<?> getEntityService() {
            return ClientService.class;
        }
    },
    PROJECT {
        @Override
        public Class<?> getEntityService() {return ProjectService.class;
        }
    };

    public abstract Class<?> getEntityService();
}



