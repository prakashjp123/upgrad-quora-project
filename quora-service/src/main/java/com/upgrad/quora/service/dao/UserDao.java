package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.UserAuthTokenEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This methods creates new user from given UserEntity object
     *
     * @param userEntity the UserEntity object from which new user will be created
     *
     * @return UserEntity object
     */
    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    /**
     * This method helps find existing user by user name
     *
     * @param userName the user name which will be searched in database for existing
     *                 user
     *
     * @return UserEntity object if user with requested user name exists in database
     */
    public UserEntity getUserByUserName(final String userName) {
        try {
            return entityManager.createNamedQuery("userByUserName", UserEntity.class).setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method helps find existing user by email id
     *
     * @param email the email id which will be searched in database for existing
     *              user
     *
     * @return UserEntity object if user with requested email id exists in database
     */
    public UserEntity getUserByEmail(final String email) {
        try {
            return entityManager.createNamedQuery("userByEmail", UserEntity.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method helps find existing user by uuid
     *
     * @param uuid the user id which will be searched in database for existing user
     *
     * @return UserEntity object if user with requested uuid exists in database
     */
    public UserEntity getUserByUuid(final String uuid) {
        try {
            return entityManager.createNamedQuery("userByUuid", UserEntity.class).setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method helps to create auth token
     *
     * @param userAuthTokenEntity user auth token entity
     * @return persisted user auth token entity
     */
    public UserAuthTokenEntity createAuthToken(final UserAuthTokenEntity userAuthTokenEntity) {
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }

    /**
     * This method helps to retrieve the user auth token entity
     *
     * @param accessToken authentication token of user
     * @return persisted user auth token entity
     */
    public UserAuthTokenEntity getUserAuthToken(final String accessToken) {
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken", UserAuthTokenEntity.class)
                    .setParameter("accessToken", accessToken).getSingleResult();
        } catch (NoResultException nre) {

            return null;
        }
    }

    /**
     * This method helps to authenticate user
     *
     * @param userName username of the user entity
     * @param password password of the user entity
     * @return user entity
     */
    public UserEntity authenticateUser(final String userName, final String password) {
        try {
            return entityManager.createNamedQuery("authenticateUserQuery", UserEntity.class)
                    .setParameter("userName", userName).setParameter("password", password).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method helps to update user entity
     *
     * @param updatedUserEntity user entity to be updated
     */
    public void updateUser(final UserEntity updatedUserEntity) {
        entityManager.merge(updatedUserEntity);
    }

    /**
     * This method helps to update the user log out.
     *
     * @param userAuthTokenEntity user auth token entity
     * @return logged out user auth token entity
     */
    public UserAuthTokenEntity updateUserLogOut(final UserAuthTokenEntity userAuthTokenEntity) {
        try {
            return entityManager.merge(userAuthTokenEntity);
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method helps to delete an user
     *
     * @param uuid uuid of an user to be deleted
     */
    public void deleteUser(String uuid) {
        UserEntity userEntity = getUserByUuid(uuid);
        entityManager.remove(userEntity);
    }

}