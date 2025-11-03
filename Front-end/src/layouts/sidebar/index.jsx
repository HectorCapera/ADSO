//react-router  packages
import { NavLink } from "react-router-dom";
import { useState } from "react";

//Packages

// react-icons
import { IoIosArrowBack } from "react-icons/io";
import { BsFillPersonVcardFill } from "react-icons/bs";
import { AiOutlineAppstore } from "react-icons/ai";
import { GrSchedules } from "react-icons/gr";

//components
import SubMenu from "./SubMenu";
import { CiSettings } from "react-icons/ci";

const Sidebar = () => {
  const Sidebar_animation = {
    // System view

    open: {
      width: "16rem",
      transition: {
        damping: 40,
      },
    },
    closed: {
      width: "4rem",
      transition: {
        damping: 40,
      },
    },
  };

  const [isOpen, setIsOpen] = useState(true);
  return (
    <div>
      <motion.div
        variants={Sidebar_animation}
        animate={isOpen ? "open" : "closed"}
        className="bg-white text-gray shadow-xl z-[999] w-[16rem] max-w[16rem] h-screen overflow-hidden md:relative fixed"
      >
        {/* logo */}
        <div className="flex items-center gap-2.5 font-medium border-b border-slate-300 py-3 mx-3">
          <img
            src="https://img.icons8.com/color/512/firebase.png"
            https:alt="logo"
            width={45}
          />
          <span className="text-xl whitespace-space-pre"> Fireball</span>
        </div>

        {/* Navigation links */}
        <div className="flex flex-col h-full ">
          {/*first*/}
          <ul className="whitespace-pre  px-2.5  text-[0.9rem] py-5 flex  flex-col  gap-1  font  medium overflow-x-hidden">
            <li>
              <NavLink to="/" className={"link"}>
                <AiOutlineAppstore size={23} className="min-w-max" />
                All apps
              </NavLink>
            </li>
            <li>
              <NavLink to="/pacientes" className={"link"}>
                <BsFillPersonVcardFill size={23} className="min-w-max" />
                Pacientes
              </NavLink>
            </li>

            {/*submenu*/}

            <div className="border-y py-5 border-slate-300">
              <small className="pl-3 text-slate-500-inline-block mb-2">
                Categorías
              </small>
            </div>

            <li>
              <NavLink to="/citas" className={"link"}>
                <GrSchedules size={23} className="min-w-max" />
                Citas
              </NavLink>
            </li>
          </ul>

          {/*second*/}
          <div className=""></div>
        </div>

        {/* Control arrow button */}
        <motion.div
          animate={
            isOpen
              ? {
                  x: 0,
                  y: 0,
                  rotate: 0,
                }
              : {
                  x: -10,
                  y: -200,
                  rotate: -180,
                }
          }
          transition={{
            duration: 0,
          }}
          onClick={() => setIsOpen(!isOpen)}
          className="absolute w-fit h-fit z-50 right-2 bottom-5 cursor-pointer md:block hidden"
        >
          {/* rect icon */}
          <IoIosArrowBack size={25} />
        </motion.div>
      </motion.div>
    </div>
  );
};

export default Sidebar;
