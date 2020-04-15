/**
 * Clear all the items in local storage.
 */
import {Configurations} from "../configurations/configurations";

export const clear = (): void => {
  localStorage.clear();
};
/**
 * Get the value associated with the specified key in local storage.
 *
 * @param key {string} Key for which associated value needs to be retrieved
 * @returns {*} Value associated with specified key
 */
export const get = (key: string): any => {
  return localStorage.getItem(`${Configurations.APPLICATION_NAME}-${key}`) || undefined;
};
/**
 * Get the value associated with the specified key in local storage.
 *
 * @param key {string} Key for which associated value needs to be retrieved
 * @returns {*} Value associated with specified key
 */
export const getObject = (key: string): any => {
  return JSON.parse(localStorage.getItem(`${Configurations.APPLICATION_NAME}-${key}`)) || undefined;
};
/**
 * Get the number of items in the local storage.
 *
 * @returns {number} Number of items in the local storage
 */
export const length = (): number => {
  return localStorage.length;
};
/**
 * Remove a key and its associated value from local storage.
 *
 * @param key {string} Key that needs to be removed from local storage
 */
export const remove = (key: string): void => {
  localStorage.removeItem(`${Configurations.APPLICATION_NAME}-${key}`);
};
/**
 * Insert or update the value of the specified key in local storage.
 *
 * @param key {string} Key that needs to be insert or updated in local storage
 * @param value {*} Value that needs to be associated to the key
 */
export const set = (key: string, value: any): void => {
  localStorage.setItem(`${Configurations.APPLICATION_NAME}-${key}`, value);
};
/**
 * Insert or update the value of the specified key in local storage.
 *
 * @param key {string} Key that needs to be insert or updated in local storage
 * @param value {*} Value that needs to be associated to the key
 */
export const setObject = (key: string, value: any): void => {
  localStorage.setItem(`${Configurations.APPLICATION_NAME}-${key}`, JSON.stringify(value));
};
