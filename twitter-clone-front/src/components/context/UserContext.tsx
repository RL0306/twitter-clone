import React, {useState, createContext} from "react";
import { IAuthUser, IUserContextType } from "../../interface/IAuthUser";



export const UserContext = createContext<IUserContextType| null>(null);

type UserContextProviderProps  = {
  children : React.ReactNode;
}


export const UserProvider = ({children} : UserContextProviderProps)  => {
  const [user, setUser] = useState<IAuthUser|null>(null);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  )
  

  
}