package api.config;

import services.*;

public enum EntityConfiguration {


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
    },
    USER {
        @Override
        public Class<?> getEntityService() {return UserService.class;
        }
    },
    TIME_ENTRY {
        @Override
        public Class<?> getEntityService() {return TimeEntryService.class;
        }
    },
    PROJECTS {
        @Override
        public Class<?> getEntityService() {return ProjectsService.class;
        }
    };

    public abstract Class<?> getEntityService();
}



