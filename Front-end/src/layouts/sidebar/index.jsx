import { motion } from "framer-motion";
import { useState } from "react";

// react-icons
import { IoIosArrowBack } from "react-icons/io";

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
        <div className="flex items-center gap-2.5 font-medium border-b border-slate-300 py-3 mx-3">
          <img
            src="https://img.icons8.com/color/512/firebase.png"
            https:alt="logo"
            width={45}
          />
          <span className="text-xl whitespace-space-pre"> Fireball</span>
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
